<?php

$data = file_get_contents('data.txt');

class State {
    const NORMAL = 'normal';
    const GARBAGE = 'garbage';
    const ESCAPE = 'escape';
}

$garbageCharacters = 0;
$previousState = State::NORMAL;
$state = State::NORMAL;

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
            default:
                $garbageCharacters++;
                break;
        }
        continue;
    }

    if ($char == '<') {
        $previousState = $state;
        $state         = State::GARBAGE;
    }
}

echo $garbageCharacters.PHP_EOL;