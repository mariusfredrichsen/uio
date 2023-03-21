#pragma once
#include "AnimationWindow.h"
#include "widgets/Button.h"

// De forskjellige tilstandene en Tile kan vaere i
enum class Cell { closed, open, flagged }; 

class Tile : public TDT4102::Button
{
	Cell state = Cell::closed;
	void set_label(std::string s) { this->setLabel(s);}
	void set_label_color(TDT4102::Color c) {this->setLabelColor(c);}
public:
	Tile(TDT4102::Point pos, int size);


	void open();
	void flag();
	bool getIsMine();
	void setIsMine(bool ball);
	void setState(Cell c) {state = c;}

	Cell getState() const { return state; };
	void setAdjMines(int n);
private:
	bool isMine = false;

};
