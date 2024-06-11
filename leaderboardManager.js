// leaderboardManager.js
const fs = require('fs');
const path = require('path');
const leaderboardFile = path.join(__dirname, 'leaderboard.txt');

// Read the leaderboard from the file
function readLeaderboard() {
    return new Promise((resolve, reject) => {
        fs.readFile(leaderboardFile, 'utf8', (err, data) => {
            if (err) {
                reject(err);
                return;
            }
            const lines = data.split('\n').filter(line => line);
            const scores = lines.map(line => {
                const [username, score] = line.split(',');
                return { username, score: parseInt(score, 10) };
            });
            resolve(scores);
        });
    });
}

// Write the updated leaderboard to the file
function writeLeaderboard(scores) {
    const data = scores.map(s => `${s.username},${s.score}`).join('\n');
    return new Promise((resolve, reject) => {
        fs.writeFile(leaderboardFile, data, 'utf8', (err) => {
            if (err) {
                reject(err);
                return;
            }
            resolve();
        });
    });
}

// Update the leaderboard with a new score
function updateLeaderboard(newUser, newScore) {
    return readLeaderboard()
        .then(scores => {
            scores.push({ username: newUser, score: newScore });
            scores.sort((a, b) => b.score - a.score); // Sort descending
            return writeLeaderboard(scores.slice(0, 5)); // Keep only top 5 scores
        });
}

module.exports = { updateLeaderboard, readLeaderboard };
