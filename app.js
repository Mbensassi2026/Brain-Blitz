// app.js for Node.js backend
const express = require('express');
const { updateLeaderboard, readLeaderboard } = require('./leaderboardManager');

const app = express();
app.use(express.json()); // for parsing application/json

const PORT = 3000;

// Route to submit a new score
app.post('/submit-score', (req, res) => {
    const { username, score } = req.body;
    updateLeaderboard(username, score)
        .then(() => res.status(200).send('Score updated successfully'))
        .catch(err => res.status(500).send('Error updating score: ' + err.message));
});

// Route to get the leaderboard
app.get('/leaderboard', (req, res) => {
    readLeaderboard()
        .then(scores => res.json(scores))
        .catch(err => res.status(500).send('Error reading leaderboard: ' + err.message));
});

app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
