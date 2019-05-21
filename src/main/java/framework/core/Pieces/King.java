package framework.core.Pieces;

import framework.core.Board;
import framework.core.Coordinate;
import framework.core.Direction;
import framework.core.Side;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Objects.hash;

public class King extends Piece {

    private final String name = "King";

    private boolean initPos = false;

    public King(Side side, Coordinate coordinate) {
        super(side, coordinate);
    }

    @Override
    public String getPieceName() {
        return name;
    }

    public void setInitPos(boolean initPos) {
        this.initPos = initPos;
    }

    @Override
    public Map<Direction, List<Coordinate>> getPossibleMoves() {

        Map<Direction, List<Coordinate>> moves = new HashMap<>();

        Coordinate coordinate = this.getCoordinate();
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        //North East
        int tempRow1 = row - 1;
        int tempCol1 = col + 1;
        Coordinate tempCoordinate1 = new Coordinate(tempRow1, tempCol1);
        if (Board.inBounds(tempCoordinate1)) {

            List<Coordinate> movesNE = new ArrayList<>();
            movesNE.add(tempCoordinate1);
            moves.put(Direction.NorthEast, movesNE);
        }

        //North West
        int tempRow2 = row - 1;
        int tempCol2 = col - 1;
        Coordinate tempCoordinate2 = new Coordinate(tempRow2, tempCol2);
        if (Board.inBounds(tempCoordinate2)) {
            List<Coordinate> movesNW = new ArrayList<>();
            movesNW.add(tempCoordinate2);
            moves.put(Direction.NorthWest, movesNW);
        }

        //South West
        int tempRow3 = row + 1;
        int tempCol3 = col - 1;
        Coordinate tempCoordinate3 = new Coordinate(tempRow3, tempCol3);
        if (Board.inBounds(tempCoordinate3)) {
            List<Coordinate> movesSW = new ArrayList<>();
            movesSW.add(tempCoordinate3);
            moves.put(Direction.SouthWest, movesSW);
        }

        //South East
        int tempRow4 = row + 1;
        int tempCol4 = col + 1;
        Coordinate tempCoordinate4 = new Coordinate(tempRow4, tempCol4);
        if (Board.inBounds(tempCoordinate4)) {
            List<Coordinate> movesSE = new ArrayList<>();
            movesSE.add(tempCoordinate4);
            moves.put(Direction.SouthEast, movesSE);
        }


        //Castling
        if (initPos) {

            int tempCol5 = 1;
            int tempCol6 = 6;

            Coordinate tempCoordinate5 = new Coordinate(row, tempCol5);
            Coordinate tempCoordinate6 = new Coordinate(row, tempCol6);

            List<Coordinate> movesOther = new ArrayList<>();
            movesOther.add(tempCoordinate5);
            movesOther.add(tempCoordinate6);

            moves.put(Direction.Castling, movesOther);

        }

        //East
        int tempCol7 = col + 1;
        Coordinate tempCoordinate7 = new Coordinate(row, tempCol7);
        if (Board.inBounds(tempCoordinate7)) {

            List<Coordinate> movesE = new ArrayList<>();
            movesE.add(tempCoordinate7);
            moves.put(Direction.East, movesE);
        }

        //West
        int tempCol8 = col - 1;
        Coordinate tempCoordinate8 = new Coordinate(row, tempCol8);
        if (Board.inBounds(tempCoordinate8)) {

            List<Coordinate> movesW = new ArrayList<>();
            movesW.add(tempCoordinate8);
            moves.put(Direction.West, movesW);
        }

        //North
        int tempRow9 = row - 1;
        Coordinate tempCoordinate9 = new Coordinate(tempRow9, col);
        if (Board.inBounds(tempCoordinate9)) {

            List<Coordinate> movesN = new ArrayList<>();
            movesN.add(tempCoordinate9);
            moves.put(Direction.North, movesN);
        }

        //South
        int tempRow10 = row + 1;
        Coordinate tempCoordinate10 = new Coordinate(tempRow10, col);
        if (Board.inBounds(tempCoordinate10)) {

            List<Coordinate> movesS = new ArrayList<>();
            movesS.add(tempCoordinate10);
            moves.put(Direction.South, movesS);
        }

        return  moves;

    }

    @Override
    public boolean hasPossibleCapture(Coordinate destination) {

        Coordinate coordinate = this.getCoordinate();
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        //North East
        int tempRow1 = row - 1;
        int tempCol1 = col + 1;
        Coordinate tempCoordinate1 = new Coordinate(tempRow1, tempCol1);
        if (destination.equals(tempCoordinate1)) {
            return true;
        }

        //North West
        int tempRow2 = row - 1;
        int tempCol2 = col - 1;
        Coordinate tempCoordinate2 = new Coordinate(tempRow2, tempCol2);
        if (destination.equals(tempCoordinate2)) {
            return true;
        }

        //South West
        int tempRow3 = row + 1;
        int tempCol3 = col - 1;
        Coordinate tempCoordinate3 = new Coordinate(tempRow3, tempCol3);
        if (destination.equals(tempCoordinate3)) {
            return true;
        }

        //South East
        int tempRow4 = row + 1;
        int tempCol4 = col + 1;
        Coordinate tempCoordinate4 = new Coordinate(tempRow4, tempCol4);
        if (destination.equals(tempCoordinate4)) {
            return true;
        }

        //East
        int tempCol7 = col + 1;
        Coordinate tempCoordinate7 = new Coordinate(row, tempCol7);
        if (destination.equals(tempCoordinate7)) {
            return true;
        }

        //West
        int tempCol8 = col - 1;
        Coordinate tempCoordinate8 = new Coordinate(row, tempCol8);
        if (destination.equals(tempCoordinate8)) {
            return true;
        }

        //North
        int tempRow9 = row - 1;
        Coordinate tempCoordinate9 = new Coordinate(tempRow9, col);
        if (destination.equals(tempCoordinate9)) {
            return true;
        }

        //South
        int tempRow10 = row + 1;
        Coordinate tempCoordinate10 = new Coordinate(tempRow10, col);
        if (destination.equals(tempCoordinate10)) {
            return true;
        }

        return false;

    }

    @Override
    public BufferedImage getImage(Side side) throws IOException {
        if (side.equals(Side.White)) {
            return ImageIO.read(new File("src/main/resources/king_white.png"));
        }
        return ImageIO.read(new File("src/main/resources/king_black.png"));
    }

    @Override
    public Piece copy() {
        King copy = new King(this.getSide(), this.getCoordinate());
        copy.initPos = this.initPos;
        return copy;
    }

    @Override
    public String toString() {
        return getSide().toString() +  " " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof King)) {
            return false;
        }
        King king = (King) obj;
        return (king.initPos == this.initPos) && (king.getSide().equals(this.getSide()))
                && (king.getCoordinate().equals(this.getCoordinate()));
    }

    @Override
    public int hashCode() {
        return hash(this.getCoordinate(), this.getSide(), this.initPos);
    }
}
