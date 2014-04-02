#pragma once
#include "sprite.h"
#include "game.h"
#ifndef PLAYER_H
#define PLAYER_H
class CPlayer : public CSprite
{
public :
	CPlayer(CGame *game);

	CGame *game;

	bool IsSwipedLeft();
	bool IsSwipedRight();
	bool IsSwipedTop();
	bool IsSwipedBottom();

	void MoveLeft();
	void MoveRight();
	void MoveTop();
	void MoveBottom();

	int GetSpeed();

	void SetSpeed(int speed);

	virtual void Update();
private:
	void MoveX(int x);
	void MoveY(int y);

	bool IntellectualTranslate(CIwFVec2 P1,CIwFVec2 P2);


	bool  oldState;
	bool swipeProcess;
	bool swipedLeft;
	bool swipedRight;
	bool swipedTop;
	bool swipedBottom;

	bool moveLeft;
	bool moveRight;
	bool moveTop;
	bool moveBottom;

	int speed;

	CIwFVec2 swipeStart;
};
#endif