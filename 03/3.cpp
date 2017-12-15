#include <cstdio>

enum Direction {
	RIGHT, TOP, LEFT, DOWN
};

Direction nextDir(Direction previous) {
	switch (previous) {
		case RIGHT:
			return TOP;
			break;
		case TOP:
			return LEFT;
			break;
		case LEFT:
			return DOWN;
			break;
		case DOWN:
			return RIGHT;
			break;	
	}
}

int main() {
	int input = 368078;

	int x = 0;
	int y = 0;
	int current = 1;
	int changeDirLimit = 1;
	int changeInRow = 0;
	int changeDirAfter = 1;
	Direction dir = RIGHT;
	for (int i = 1; i < input; ++i) {
		switch (dir) {
			case RIGHT:
				x++;
				break;
			case TOP:
				y++;
				break;
			case LEFT:
				x--;
				break;
			case DOWN:
				y--;
				break;	
		}
		changeDirAfter--;
		if (changeDirAfter == 0) {
			changeInRow++;

			if (changeInRow == 1) {
				changeDirAfter = changeDirLimit;
			} else if (changeInRow == 2) {
				changeDirLimit++;
				changeInRow = 0;
				changeDirAfter = changeDirLimit;
			}

			dir = nextDir(dir);
		}
	}
	printf("input cartesian = (%d, %d)\n", x, y);

	int result = x + y;
	result = result < 0 ? -result : result;
	printf("result = %d", result);
	return 0;
}