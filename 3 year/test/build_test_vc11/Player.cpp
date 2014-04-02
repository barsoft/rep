#include "Player.h"
#include "SolidSprite.h"


CPlayer::CPlayer(CGame* game)
{
	this->game=game;
	moveLeft=false;
	moveRight=false;
	moveTop=false;
	moveBottom=false;

	swipedLeft=false;
	swipedRight=false;
	swipedTop=false;
	swipedBottom=false;

	swipeProcess=false;

	CIwFVec2 mousePosition((float)s3ePointerGetX(), (float)s3ePointerGetY());

	swipeStart.x=mousePosition.x;
	swipeStart.y=mousePosition.y;

	speed=4;
}

bool CPlayer::IsSwipedLeft()
{
	return swipedLeft;
}

bool CPlayer::IsSwipedRight()
{
	return swipedRight;
}

bool CPlayer::IsSwipedTop()
{
	return swipedTop;
}

bool CPlayer::IsSwipedBottom()
{
	return swipedBottom;
}

void CPlayer::Update()
{
	CIwFVec2 mousePosition((float)s3ePointerGetX(), (float)s3ePointerGetY());

	bool newState=s3ePointerGetState(S3E_POINTER_BUTTON_SELECT) & S3E_POINTER_STATE_DOWN;

	swipeProcess=false;

	swipedLeft=false;
	swipedRight=false;
	swipedTop=false;
	swipedBottom=false;

	if ( newState )
	{
		swipeProcess=true;
		if (!oldState)
			swipeStart=CIwFVec2(mousePosition.x,mousePosition.y);
	}
	else if (oldState){
		if (swipeStart.x-mousePosition.x>100)
		{
			swipedLeft=true;

		}
		else
			if (swipeStart.x-mousePosition.x<-100)
			{	
				swipedRight=true;
			}
			else
				if (swipeStart.y-mousePosition.y>100)
				{
					swipedTop=true;
				}
				else
					if (swipeStart.y-mousePosition.y<-100)
					{	
						swipedBottom=true;
					}
	}

	oldState=newState;


	if (moveLeft)
	{
		MoveX(GetX()-speed);
	}
	else
		if (moveRight)
		{
			MoveX(GetX()+speed);
		}
		else
			if (moveTop)
			{
				MoveY(GetY()-speed);
			}

			else
				if (moveBottom)
				{
					MoveY(GetY()+speed);
				}
}

void CPlayer::MoveLeft()
{
	moveLeft=true;
}

void CPlayer::MoveRight()
{
	moveRight=true;
}

void CPlayer::MoveTop()
{
	moveTop=true;
}

void CPlayer::MoveBottom()
{
	moveBottom=true;
}

void CPlayer::SetSpeed( int speed )
{
	this->speed=speed;
}

int CPlayer::GetSpeed()
{
	return speed;
}

bool Intersects(CIwFVec2 P1, CIwFVec2 P2, CIwFVec2 P3, CIwFVec2 P4)
{
	return ! ( P2.y < P3.y || P1.y > P4.y || P2.x < P3.x || P1.x > P4.x );
}


void CPlayer::MoveX( int x )
{
	CIwFVec2 P1=CIwFVec2(x,GetY());
	CIwFVec2 P2=CIwFVec2(x+GetWidth(),GetY()+GetHeight());

	if (!IntellectualTranslate(P1,P2))
		return;

	SetX(x);
}

void CPlayer::MoveY( int y )
{
	CIwFVec2 P1=CIwFVec2(GetX(),y);
	CIwFVec2 P2=CIwFVec2(GetX()+GetWidth(),y+GetHeight());

	if (!IntellectualTranslate(P1,P2))
		return;

	SetY(y);
}

bool CPlayer::IntellectualTranslate(CIwFVec2 P1,CIwFVec2 P2)
{
	for (int i=0; i< game->sprites.size();i++)	
	{
		if (game->sprites[i]->GetId()==TREE)
		{
			CIwFVec2 P3=CIwFVec2(game->sprites[i]->GetX(),game->sprites[i]->GetY());
			CIwFVec2 P4=CIwFVec2(game->sprites[i]->GetX()+game->sprites[i]->GetWidth(),game->sprites[i]->GetY()+game->sprites[i]->GetHeight());
			if (Intersects(P1,P2,P3,P4))
			{
				moveLeft=false;
				moveRight=false;
				moveTop=false;
				moveBottom=false;
				return false;
			}
		}
		if (game->sprites[i]->GetId()==STAR)
		{
			CIwFVec2 P3=CIwFVec2(game->sprites[i]->GetX(),game->sprites[i]->GetY());
			CIwFVec2 P4=CIwFVec2(game->sprites[i]->GetX()+game->sprites[i]->GetWidth(),game->sprites[i]->GetY()+game->sprites[i]->GetHeight());
			if (Intersects(P1,P2,P3,P4))
			{
				CSprite* p=game->sprites[i];
				game->sprites.erase(game->sprites.begin()+i);
				delete p;
			}
		}
	}
	return true;
}


