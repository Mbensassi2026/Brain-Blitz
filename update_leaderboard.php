<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $username = $_POST['username'];
    $score = (int)$_POST['score'];

    $file = 'leaderboard.txt';
    $leaderboard = file_exists($file) ? json_decode(file_get_contents($file), true) : [];

    $leaderboard[$username] = max($score, isset($leaderboard[$username]) ? $leaderboard[$username] : 0);
    arsort($leaderboard); // Sort by score in descending order

    file_put_contents($file, json_encode($leaderboard));

    $formattedLeaderboard = "";
    foreach ($leaderboard as $user => $score) {
        $formattedLeaderboard .= "{$user}: {$score}\n";
    }
    echo $formattedLeaderboard;
} else {
    echo "Invalid request method.";
}
?>
