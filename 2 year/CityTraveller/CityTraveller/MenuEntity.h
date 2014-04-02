#pragma once
#include "ButtonEntity.h"
#include "StaticEntity.h"
#include "SlideshowEntity.h"
void SlideshowThread(void *dummy);
class MenuEntity:public GameEntity
{
private:
	int y;
	ButtonEntity NewGameButton;
	ButtonEntity PlayButton;
	ButtonEntity OptionsButton;
		ButtonEntity OptionsResolutionButton;
			ButtonEntity OptionsResolution1280x800Button;
			ButtonEntity OptionsResolution1600x900Button;
			ButtonEntity OptionsResolution1920x1080Button;
			ButtonEntity OptionsResolutionBackButton;
		ButtonEntity OptionsMusicOnButton;
		ButtonEntity OptionsMusicOffButton;
		ButtonEntity OptionsBackButton;
	ButtonEntity HighscoreButton;
	ButtonEntity HelpButton;
	ButtonEntity ExitButton;
	StaticEntity Logo;
	SlideshowEntity Slideshow;
		
	enum MenuState
	{
		nil=NULL,
		Options,
			OptionsResolution,
			Help,
			Highscore
	};
	enum MusicState
	{
		OptionsMusicOn,
		OptionsMusicOff
	};
	MenuState ms;
	MusicState muss;
public:
	MenuEntity()
	{
		y=-50;
		Logo.InitObject(-260,60,-140,200,"Data\\EuroIcon.bmp");
		NewGameButton.InitObject(-200,100+y,"New Game",StandardStyle);
		PlayButton.InitObject(-200,100+y,"Play",StandardStyle);
		OptionsButton.InitObject(-200,70+y,"Options",StandardStyle);
			OptionsResolutionButton.InitObject(-200,100+y,"Resolution",StandardStyle);
				OptionsResolution1280x800Button.InitObject(-200,100+y,"1280x800",StandardStyle);
				OptionsResolution1600x900Button.InitObject(-200,70+y,"1600x900",StandardStyle);
				OptionsResolution1920x1080Button.InitObject(-200,40+y,"1920x1080",StandardStyle);
				OptionsResolutionBackButton.InitObject(-200,10+y,"Back",StandardStyle);
			OptionsMusicOnButton.InitObject(-200,70+y,"Music On",StandardStyle);
			OptionsMusicOffButton.InitObject(-200,70+y,"Music Off",StandardStyle);
			OptionsBackButton.InitObject(-200,40+y,"Back",StandardStyle);
		HighscoreButton.InitObject(-200,40+y,"Highscore",StandardStyle);
		HelpButton.InitObject(-200,10+y,"Help",StandardStyle);
		ExitButton.InitObject(-200,-20+y,"Exit",StandardStyle);
		Slideshow.InitObject(-130,-210,280,210);
		gs=IsMenuGame;
		ms=nil;
		muss=OptionsMusicOn;
	}
	void LoadTextures();
	void OnPaint();
	void ReleaseTextures();
	void NextSlide();
};

void MenuEntity::LoadTextures()
{
	Logo.LoadTextures();
	PlayButton.LoadTextures();
	NewGameButton.LoadTextures();
	OptionsButton.LoadTextures();
		OptionsResolutionButton.LoadTextures();
			OptionsResolution1280x800Button.LoadTextures();
			OptionsResolution1600x900Button.LoadTextures();
			OptionsResolution1920x1080Button.LoadTextures();
			OptionsResolutionBackButton.LoadTextures();
		OptionsBackButton.LoadTextures();
		OptionsMusicOnButton.LoadTextures();
		OptionsMusicOffButton.LoadTextures();
	HighscoreButton.LoadTextures();
	HelpButton.LoadTextures();
	ExitButton.LoadTextures();
	Slideshow.LoadTextures();
}
void MenuEntity::OnPaint()
{
	if (gs==IsMenuGame && ms==nil)
	{
		y=-50;
		PlayButton.InitObject(-200,100+y,"Play",StandardStyle);
		OptionsButton.InitObject(-200,70+y,"Options",StandardStyle);
		HighscoreButton.InitObject(-200,40+y,"Highscore",StandardStyle);
		HelpButton.InitObject(-200,10+y,"Help",StandardStyle);
		ExitButton.InitObject(-200,-20+y,"Exit",StandardStyle);

		PlayButton.OnPaint();
		if (PlayButton.OnSelect()) {gs=IsPlayGame; return;}

		OptionsButton.OnPaint( );
		if ( OptionsButton.OnSelect()) {ms=Options;}

		HighscoreButton.OnPaint( );
		if (HighscoreButton.OnSelect()) {ms=Highscore;}

		HelpButton.OnPaint( );
		if (HelpButton.OnSelect()) {ms=Help;}

		ExitButton.OnPaint( );
		if (ExitButton.OnSelect()) PostQuitMessage(0);
	}	
	if (gs==IsContinue && ms==nil)
	{
		y=-90;
		NewGameButton.InitObject(-200,130+y,"New Game",StandardStyle);
		PlayButton.InitObject(-200,100+y,"Continue",StandardStyle);
		OptionsButton.InitObject(-200,70+y,"Options",StandardStyle);
		HighscoreButton.InitObject(-200,40+y,"Highscore",StandardStyle);
		HelpButton.InitObject(-200,10+y,"Help",StandardStyle);
		ExitButton.InitObject(-200,-20+y,"Exit",StandardStyle);

		NewGameButton.OnPaint();
		if (NewGameButton.OnSelect()) {gs=IsPlayGame; return;}

		PlayButton.OnPaint();
		if (PlayButton.OnSelect()) {gs=IsPlayGame; return;}
		
		OptionsButton.OnPaint( );
		if ( OptionsButton.OnSelect()) {ms=Options;}
		
		HighscoreButton.OnPaint( );
		if (HighscoreButton.OnSelect()) {ms=Highscore;}
		
		HelpButton.OnPaint( );
		if (HelpButton.OnSelect()) {ms=Help;}

		ExitButton.OnPaint( );
		if (ExitButton.OnSelect()) PostQuitMessage(0);
	}
	if (ms==Options)
	{
		OptionsResolutionButton.OnPaint();
		if (OptionsResolutionButton.OnSelect()) { ms=OptionsResolution; ms=OptionsResolution;}
		
		if ( muss==OptionsMusicOff) 
		{
			OptionsMusicOnButton.OnPaint();
			if (OptionsMusicOnButton.OnSelect()) {PlaySound(NULL,NULL,NULL); muss=OptionsMusicOn;}
		}
		if ( muss==OptionsMusicOn) 
		{
			OptionsMusicOffButton.OnPaint();
			if (OptionsMusicOffButton.OnSelect()) {/*_beginthread(MusicThread,0,NULL);*/ muss=OptionsMusicOff;}
		}
		OptionsBackButton.OnPaint();
		if (OptionsBackButton.OnSelect()) {ms=nil; }
	}
	if (ms==OptionsResolution)
	{
		OptionsResolution1280x800Button.OnPaint();
		if (OptionsResolution1280x800Button.OnSelect()) {SetWindowPos(  g_hWnd,HWND_TOP,1,1,1280,800,NULL);}
		OptionsResolution1600x900Button.OnPaint();
		if (OptionsResolution1600x900Button.OnSelect()) {SetWindowPos(  g_hWnd,HWND_TOP,1,1,1600,900,NULL);}
		OptionsResolution1920x1080Button.OnPaint();
		if (OptionsResolution1920x1080Button.OnSelect()) {SetWindowPos(  g_hWnd,HWND_TOP,1,1,1920,1080,NULL);}
		OptionsResolutionBackButton.OnPaint();
		if (OptionsResolutionBackButton.OnSelect()) {ms=Options; }
	}
	Logo.OnPaint();
	Slideshow.OnPaint();
}
void MenuEntity::ReleaseTextures()
{
	Logo.ReleaseTextures();
	PlayButton.ReleaseTextures();
	NewGameButton.ReleaseTextures();
	OptionsButton.ReleaseTextures();
		OptionsResolutionButton.ReleaseTextures();
		OptionsBackButton.ReleaseTextures();
	HighscoreButton.ReleaseTextures();
	HelpButton.ReleaseTextures();
	ExitButton.ReleaseTextures();
	Slideshow.ReleaseTextures();
}
void MenuEntity::NextSlide()
{
	Slideshow.NextSlide();
}
