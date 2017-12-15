#include <cstdio>
#include <cmath>
#include <map>

enum Direction {
	RIGHT, TOP, LEFT, DOWN
};

struct position {
	int x;
	int y;
	position(int x, int y) {
		this->x = x;
		this->y = y;
	}
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

position indexToCartesian(int index) {
	int x = 0;
	int y = 0;
	int current = 1;
	int changeDirLimit = 1;
	int changeInRow = 0;
	int changeDirAfter = 1;
	Direction dir = RIGHT;
	for (int i = 1; i < index; ++i) {
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
	position pos(x, y);
	return pos;
}

double distance(position pos1, position pos2) {
	return sqrt(
		pow(pos2.x - pos1.x, 2) + pow(pos2.y - pos1.y, 2)
		);
}

int main() {
	int limit = 368078;
	std::map<int, int> history; 

	position curPos(0, 0);
	int current = 1;
	int changeDirLimit = 1;
	int changeInRow = 0;
	int changeDirAfter = 1;
	Direction dir = RIGHT;
	for (int i = 1; ; ++i) {
		if (i == 1) {
			history.insert(std::pair<int,int>(1, 1));
		} else {
			int sum = 0;
			for (std::map<int, int>::iterator it = history.begin(); it != history.end(); ++it)
			{
				position otherElement = indexToCartesian(it->first);
				double dist = distance(curPos, otherElement);
				printf("%d (%d, %d) <-> %d (%d, %d): %.2f\n", i, curPos.x, curPos.y, it->first, otherElement.x, otherElement.y, dist);
				if (dist < 2) {
					sum += it->second;
				}
			}

			// final check
			if (sum > limit) {
				printf("FOUND: %d\n", sum);
				break;
			}

			history.insert(std::pair<int, int>(i, sum));
		}

		switch (dir) {
			case RIGHT:
				curPos.x++;
				break;
			case TOP:
				curPos.y++;
				break;
			case LEFT:
				curPos.x--;
				break;
			case DOWN:
				curPos.y--;
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

	return 0;
}