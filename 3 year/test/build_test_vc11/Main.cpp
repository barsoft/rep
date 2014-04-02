#include "s3e.h"
#include "Iw2D.h"
#include "IwGxFont.h"
#include "IwResManagerClass.h"
#include "IwGx.h"
#include "game.h"

// updates per second
#define UPS 60

// throttle frame time at 10 fps (i.e. the game will slow down rather
// than having very low frame rate)
#define MAX_UPDATES 6

int GetUpdateFrame()
{
	return (int)(s3eTimerGetMs() / (1000/UPS));
}

// "main" is the S3E entry point
int main()
{
	Iw2DInit();
	IwGxInit();
	IwResManagerInit();
	// Load the group containing the "arial14" font
	IwGetResManager()->LoadGroup("IwGxFontTTF.group");

	IwGxSetScreenOrient(IW_GX_ORIENT_90);
	// create game object
	CGame* pGame = new CGame;

	int currentUpdate = GetUpdateFrame();
	int nextUpdate = currentUpdate;

	// to exit correctly, applications should poll for quit requests
	while(!s3eDeviceCheckQuitRequest())
	{
		// run logic at a fixed frame rate (defined by UPS)

		// block until the next frame (don't render unless at
		// least one update has occurred)
		while(!s3eDeviceCheckQuitRequest())
		{
			nextUpdate = GetUpdateFrame();
			if( nextUpdate != currentUpdate )
				break;
			s3eDeviceYield(1);
		}

		// execute update steps
		int frames = nextUpdate - currentUpdate;
		frames = MIN(MAX_UPDATES, frames);
		while(frames--)
		{
			pGame->Update();
		}
		currentUpdate = nextUpdate;

		// render the results
		pGame->Render();

		// if an application uses polling input the application
		// must call update once per frame
		s3ePointerUpdate();
		s3eKeyboardUpdate();

		// S3E applications should yield frequently
		s3eDeviceYield();
	}

	// clear up game object
	delete pGame;

	Iw2DTerminate();
	IwGxTerminate();

	return 0;
}
