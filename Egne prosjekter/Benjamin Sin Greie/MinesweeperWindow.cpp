#include "MinesweeperWindow.h"
#include <random>
#include <iostream>
#include "std_lib_facilities.h"
#include <vector>

MinesweeperWindow::MinesweeperWindow(int x, int y, int width, int height, int mines, const string &title) : 
	// Initialiser medlemsvariabler, bruker konstruktoren til AnimationWindow-klassen
	AnimationWindow{x, y, width * cellSize, (height + 3) * cellSize, title},
	width{width}, height{height}, mines{mines}, restart{{static_cast<int>(cellSize*(width-3)),(height+2)*cellSize},3*cellSize,cellSize,"restart"}
,	 quit{{static_cast<int>(0.5*cellSize),(height+2)*cellSize},3*cellSize,cellSize,"quit"}
,	 gameL{{static_cast<int>(cellSize*(width/2-1.5)),(height+2)*cellSize},static_cast<int>(3.5*cellSize),cellSize,"You lost"}
,	 gameW{{static_cast<int>(cellSize*(width/2-1.5)),(height+2)*cellSize},static_cast<int>(3.5*cellSize),cellSize,"You won"}
{
	
	// Legg til alle tiles i vinduet
	random_device dev;
    mt19937 rng(dev());
    uniform_int_distribution<mt19937::result_type> dist(0,width*height-1);
	vector<unsigned> minePlacement{static_cast<unsigned>(dist(rng))};
	int counter = 1;
	while (counter < mines){
		unsigned temp2 = dist(rng);
		if (find(minePlacement.begin(), minePlacement.end(), temp2) == minePlacement.end())
			minePlacement.push_back(temp2);
			counter ++;
	}

	for (int i = 0; i < height; ++i) {
		for (int j = 0; j < width; ++j) {
			tiles.emplace_back(new Tile{ Point{j * cellSize, i * cellSize}, cellSize});
			tiles.back()->setCallback(std::bind(&MinesweeperWindow::cb_click, this));
			auto temp = tiles.back().get();
			add(*temp); 
		}
	}
	for(int i=0; i < size(minePlacement);i++) {
		tiles[minePlacement[i]]->setIsMine(true);
	}
	minePlacement.clear();
	add(quit);
	add(restart);
	add(gameW);
	quit.setCallback(bind(&MinesweeperWindow::cb_quit, this));
    restart.setCallback(bind(&MinesweeperWindow::cb_restart, this));
}

vector<Point> MinesweeperWindow::adjacentPoints(Point xy) const {
	vector<Point> points;
	for (int di = -1; di <= 1; ++di) {
		for (int dj = -1; dj <= 1; ++dj) {
			if (di == 0 && dj == 0) {
				continue;
			}

			Point neighbour{ xy.x + di * cellSize,xy.y + dj * cellSize };
			if (inRange(neighbour)) {
				points.push_back(neighbour);
			}
		}
	}
	return points;
}

void MinesweeperWindow::openTile(Point xy) {
	cout<<"Breakpoint?"<<xy.x<<","<<xy.y;
	if(at(xy)->getState() == Cell::closed) {
		cout<<"No"<<endl;
		at(xy)->open();
		openTiles+=1;
		if(at(xy)->getIsMine()) {
			for(int i = 0; i < width*height; i++) {
			gameW.setText("You Lose");
			if(tiles[i]->getState() == Cell::closed) {
				
				if(tiles[i]->getIsMine()) {
					tiles[i]->open();
				}
			}
		}
		}
		else if (countMines(adjacentPoints(xy)) == 0){
			if(xy.x+cellSize < (width-0.1)*cellSize) {
				openTile({xy.x+cellSize, xy.y});
			}
			if(xy.x-cellSize > 0) {
				openTile({xy.x-cellSize, xy.y});
			}
			if(xy.y-cellSize > 0) {
				openTile({xy.x, xy.y-cellSize});
			}
			if(xy.y+cellSize <(height-0.1)*cellSize) {
				openTile({xy.x, xy.y+cellSize});
			}
			
			
		}
		else {
			int count = countMines(adjacentPoints(xy));
			at(xy)->setAdjMines(count);
		}

	}
	if(openTiles == width*height-mines) {
		gameW.setText("You won");
		for(int i = 0; i < width*height; i++) {
			if(tiles[i]->getState() == Cell::closed) {
				if(tiles[i]->getIsMine()) {
					tiles[i]->flag();
				}
			}
		}
	}

}

void MinesweeperWindow::flagTile(Point xy) {
	if(at(xy)->getState() != Cell::open) {
		at(xy)->flag();
	}
	
}

int MinesweeperWindow::countMines(vector<Point> coords) const{
	int counter = 0;
	for(int i = 0; i < size(coords); i++) {
		if(at(coords[i])->getIsMine()) {
			counter ++;
		}
	}
	return counter;
}

//Kaller openTile ved venstreklikk og flagTile ved hoyreklikk
void MinesweeperWindow::cb_click() {
	
	Point xy{this->get_mouse_coordinates()};
	//std::cout << xy.x << " " << xy.y <<": " << xy.x / (cellSize) + (xy.y / cellSize) * width<<"\n";

	if (!inRange(xy)) {
		return;
	}
	if (this->is_left_mouse_button_down()) {
		openTile(xy);
	}
	else if(this->is_right_mouse_button_down()){
		flagTile(xy);
	}
}

void MinesweeperWindow::cb_restart() {
	random_device dev;
    mt19937 rng(dev());
    uniform_int_distribution<mt19937::result_type> dist(0,width*height-1);
	vector<unsigned> minePlacement{static_cast<unsigned>(dist(rng))};
	openTiles = 0;
	int counter = 1;
	while (counter < mines){
		unsigned temp2 = dist(rng);
		if (find(minePlacement.begin(), minePlacement.end(), temp2) == minePlacement.end())
			minePlacement.push_back(temp2);
			counter ++;
	}
	for(int i = 0; i < width*height; i++) {
		if(tiles[i]->getState() == Cell::open) {
			tiles[i]->setLabel("");
			tiles[i]->setButtonColor(Color::silver);
			tiles[i]->setState(Cell::closed);
			
		}
		else if(tiles[i]->getState() == Cell::flagged) {
			tiles[i]->flag();
		}
		if(tiles[i]->getIsMine()) {
			tiles[i]->setIsMine(false);
			tiles[i]->setState(Cell::closed);
		}
			
		}
	for(int i=0; i < size(minePlacement);i++) {
		tiles[minePlacement[i]]->setIsMine(true);
	}
}