<?php

$program = file('data.txt');

$registry = [];
foreach ($program as $instruction) {
    preg_match('/^([a-z]+) (inc|dec) (\-?)(\d+) if ([a-z]+) (>|<|==|!=|>=|<=) (\-?)(\d+)$/', $instruction, $matches);

    $register = $matches[1];
    $operation = $matches[2];
    $argument = '-' === $matches[3] ? -(int)$matches[4] : (int)$matches[4];
    $conditionRegister = $matches[5];
    $conditionOperation = $matches[6];
    $conditionArgument = '-' === $matches[7] ? -(int)$matches[8] : (int)$matches[8];

    if (!array_key_exists($register, $registry)) {
        $registry[$register] = 0;
    }
    if (!array_key_exists($conditionRegister, $registry)) {
        $registry[$conditionRegister] = 0;
    }

    $conditionRegisterValue = $registry[$conditionRegister];
    $conditionMet = false;
    switch ($conditionOperation) {
        case '==':
            $conditionMet = $conditionRegisterValue == $conditionArgument;
            break;
        case '!=':
            $conditionMet = $conditionRegisterValue != $conditionArgument;
            break;
        case '>':
            $conditionMet = $conditionRegisterValue > $conditionArgument;
            break;
        case '<':
            $conditionMet = $conditionRegisterValue < $conditionArgument;
            break;
        case '>=':
            $conditionMet = $conditionRegisterValue >= $conditionArgument;
            break;
        case '<=':
            $conditionMet = $conditionRegisterValue <= $conditionArgument;
            break;
    }

    if (!$conditionMet) {
        continue;
    }

    switch ($operation) {
        case 'inc':
            $registry[$register] += $argument;
            break;
        case 'dec':
            $registry[$register] -= $argument;
            break;
    }
}

echo max($registry).PHP_EOL;