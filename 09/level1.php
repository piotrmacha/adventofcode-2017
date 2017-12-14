<?php

$data = file_get_contents('data.txt');

class State {
    const NORMAL = 'normal';
    const GARBAGE = 'garbage';
    const ESCAPE = 'escape';
}

$base = 1;
$previousState = State::NORMAL;
$state = State::NORMAL;
$score = 0;

foreach (str_split($data) as $char) {
    if ($state === State::ESCAPE) {
        $state = $previousState;
        $previousState = State::ESCAPE;
        continue;
    }

    if ($state === State::GARBAGE) {
        switch ($char) {
            case '>':
                $state = State::NORMAL;
                $previousState = State::GARBAGE;
                break;
            case '!':
                $state = State::ESCAPE;
                $previousState = State::GARBAGE;
                break;
        }
        continue;
    }

    switch ($char) {
        case '{':
            $score += $base;
            $base++;
            break;
        case '}':
            $base--;
            break;
        case '<':
            $previousState = $state;
            $state = State::GARBAGE;
            break;
    }
}

echo $score.PHP_EOL;