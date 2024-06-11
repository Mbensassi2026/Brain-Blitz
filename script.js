let username = '';
let score = 0;
let answered = false;
let askedQuestions = [];
const totalQuestions = 8;

function startGame() {
    username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    if (!username || !password) {
        alert('Please enter a username and password');
        return;
    }

    fetch('http://127.0.0.1:8000/api/start', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            document.getElementById('login-section').classList.add('hidden');
            document.getElementById('game-section').classList.remove('hidden');
            fetchQuestion(); // Fetch the first question immediately
        } else {
            alert('Failed to start game');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function fetchQuestion() {
    if (askedQuestions.length >= totalQuestions) {
        document.getElementById('question').innerText = 'No more questions';
        document.getElementById('choices').innerHTML = '';
        displayFinalScore();
        return;
    }

    answered = false;
    document.getElementById('feedback').innerText = '';

    fetch('http://127.0.0.1:8000/api/quest', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${encodeURIComponent(username)}`
    })
    .then(response => response.json())
    .then(data => {
        if (data.question) {
            document.getElementById('question').innerText = data.question;
            const choicesContainer = document.getElementById('choices');
            choicesContainer.innerHTML = '';
            data.choices.forEach((choice, index) => {
                const button = document.createElement('button');
                button.innerText = choice;
                button.onclick = () => submitAnswer(choice, button, data.question, data.correctAnswer);
                choicesContainer.appendChild(button);
            });
        } else {
            document.getElementById('question').innerText = 'No more questions';
            document.getElementById('choices').innerHTML = '';
            displayFinalScore();
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function submitAnswer(answer, button, question, correctAnswer) {
    if (answered) return;
    answered = true;

    fetch('http://127.0.0.1:8000/api/answer', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `username=${encodeURIComponent(username)}&answer=${encodeURIComponent(answer)}`
    })
    .then(response => response.json())
    .then(data => {
        const feedback = document.getElementById('feedback');
        if (data.correct) {
            feedback.innerText = 'Correct!';
            score += 5;
        } else {
            feedback.innerText = 'Incorrect!';
        }
        updateScore();
        storeQuestion(question, correctAnswer);
        highlightAnswer(button, data.correct);

        setTimeout(() => {
            resetChoices();
            fetchQuestion();
        }, 3000); // 3-second delay before fetching the next question
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function highlightAnswer(button, correct) {
    const buttons = document.querySelectorAll('#choices button');
    buttons.forEach(btn => {
        btn.disabled = true; // Disable all buttons after an answer is selected
        if (btn === button) {
            btn.style.backgroundColor = correct ? 'green' : 'red';
            btn.style.color = 'white';
        } else {
            btn.style.backgroundColor = 'white';
            btn.style.color = 'black';
        }
    });
}

function resetChoices() {
    const buttons = document.querySelectorAll('#choices button');
    buttons.forEach(btn => {
        btn.style.backgroundColor = '#28a745';
        btn.style.color = 'white';
        btn.disabled = false;
    });
    document.getElementById('feedback').innerText = '';
}

function updateScore() {
    document.getElementById('score').innerText = `Score: ${score}/40`;
}

function storeQuestion(question, correctAnswer) {
    askedQuestions.push({ question, correctAnswer });
}

function displayFinalScore() {
    const finalScore = document.getElementById('final-score');
    finalScore.innerText = `Your final score is: ${score}/40`;

    const questionList = document.createElement('ol');
    askedQuestions.forEach((q, index) => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `<strong>Question ${index + 1}:</strong> ${q.question}<br><strong>Correct Answer:</strong> ${q.correctAnswer}`;
        questionList.appendChild(listItem);
    });

    const questionListContainer = document.getElementById('question-list-container');
    questionListContainer.innerHTML = ''; // Clear any previous content
    questionListContainer.appendChild(questionList);
    questionListContainer.classList.remove('hidden');

    document.getElementById('leaderboard-button').classList.remove('hidden');
}

function fetchLeaderboard() {
    fetch('http://127.0.0.1:8000/api/leaderboard')
    .then(response => response.json())
    .then(data => {
        const leaderboardTableBody = document.getElementById('leaderboard-table').getElementsByTagName('tbody')[0];
        leaderboardTableBody.innerHTML = ''; // Clear any previous content

        data.forEach((entry, index) => {
            const row = leaderboardTableBody.insertRow();
            const cell1 = row.insertCell(0);
            const cell2 = row.insertCell(1);
            const cell3 = row.insertCell(2);

            cell1.innerText = index + 1; // Rank
            cell2.innerText = entry.username;
            cell3.innerText = entry.score;
        });

        document.getElementById('leaderboard-container').classList.remove('hidden');
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
