<?php

$passphrases = file('data.txt');

function areAnagrams($word1, $word2) {
//    $letters1 = str_split($word1);
//    sort($letters1);
//    $letters2 = str_split($word2);
//    sort($letters2);
//
//    return $letters1 == $letters2;
    $letters = [
    ];

    foreach (str_split($word1) as $item) {
        $letters[$item] = ($letters[$item] ?? 0) + 1;
    }
    foreach (str_split($word2) as $item) {
        $letters[$item] = ($letters[$item] ?? 0) - 1;
    }

    return array_reduce($letters, function ($agg, $x) {
       return $agg + $x;
    }, 0) === 0;
}

function isValid(string $phrase) {
    $tokens = array_filter(array_map('trim', explode(' ', $phrase)));
    $seenTokens = [];

    foreach ($tokens as $token) {
        if (in_array($token, $seenTokens)) {
            return FALSE;
        }

        foreach ($seenTokens as $seenToken) {
            if (areAnagrams($seenToken, $token)) {
                return FALSE;
            }
        }

        $seenTokens[] = $token;
    }

    return TRUE;
}

$valid = 0;
foreach ($passphrases as $passphrase) {
    if (isValid($passphrase)) {
        $valid++;
    }
//    die();
}

echo $valid.PHP_EOL;