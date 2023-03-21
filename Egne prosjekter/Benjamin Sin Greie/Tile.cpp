#include "Tile.h"
#include <map>
#include "std_lib_facilities.h"

// For aa sette labelfarge i henhold til hvor mange miner som er rundt
const std::map<int, TDT4102::Color> minesToColor{{1, TDT4102::Color::blue},
												{2, TDT4102::Color::red},
												{3, TDT4102::Color::dark_green},
												{4, TDT4102::Color::dark_magenta},
												{5, TDT4102::Color::dark_blue},
												{6, TDT4102::Color::dark_cyan},
												{7, TDT4102::Color::dark_red},
												{8, TDT4102::Color::gold}};

// For aa sette Tilelabel i henhold til state
const std::map<Cell, std::string> cellToSymbol{{Cell::closed, ""},
									 			{Cell::open, ""},
									 	  		{Cell::flagged, "|>"}};

Tile::Tile(TDT4102::Point pos, int size) : 
	Button({pos.x, pos.y}, 1.5*size, size, "") {
		setButtonColor(TDT4102::Color::silver);
	}

void Tile::open()
{
	if(state == Cell::open || state == Cell::flagged) {
		string does = "nothing";
	}
	else {
		state = Cell::open;
		setButtonColor(Color::white);
		if(getIsMine()==true){
			set_label("X");
			set_label_color(Color::red);
		}
	}
}

void Tile::flag()
{
	if(state == Cell::flagged){
		state = Cell::closed;
		setButtonColor(Color::silver);
		set_label("");
	}
	else {
		state = Cell::flagged;
		set_label("|>");
		set_label_color(Color::black);
	}
}

bool Tile::getIsMine(){
	return isMine;
}

void Tile::setIsMine(bool ball){
	isMine = ball;
}

void Tile::setAdjMines(int n) {
	if(n > 0) {
		set_label(to_string(n));
		set_label_color(minesToColor.at(n));
	}
}

