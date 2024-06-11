// frontend.js for client-side interactions
function submitScore() {
    const username = document.getElementById('username').value;
    const score = parseInt(document.getElementById('score').value, 10);
    fetch('http://localhost:3000/submit-score', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, score })
    })
    .then(response => response.text())
    .then(data => alert('Response: ' + data))
    .catch(error => console.error('Error:', error));
}

function getLeaderboard() {
    fetch('http://localhost:3000/leaderboard')
    .then(response => response.json())
    .then(data => {
        const leaderboardDiv = document.getElementById('leaderboard');
        leaderboardDiv.innerHTML = '<h2>Current Top Scores</h2>';
        data.forEach((entry, index) => {
            leaderboardDiv.innerHTML += `<p>${index + 1}. ${entry.username} - ${entry.score}</p>`;
        });
    })
    .catch(error => console.error('Error:', error));
}
