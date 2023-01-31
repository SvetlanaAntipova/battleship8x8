package com.epam.rd.autotasks;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        String ship=Long.toBinaryString(this.ships);

        if(ship.length()==63)
            ship=String.valueOf(new StringBuffer(ship).insert(0,"0"));
        String[] shipSplit=ship.split("");
        String[] shotSplit=shot.split("");

        List<String> listMap=List.of("A","B","C","D","E","F","G","H");

        int index=8*(Integer.parseInt(shotSplit[1])-1)+listMap.indexOf(shotSplit[0]);
        this.shots|=1L<<63-index;

        return Objects.equals(shipSplit[index],"1");
    }

    public String state() {
        StringBuilder result = new StringBuilder();
        BigInteger mapShips = BigInteger.valueOf(ships);
        BigInteger aimShots = BigInteger.valueOf(shots);
        boolean map, aim;
        // '.' '×' '☐' '☒'
        for (int i = 0; i < 64; i++) {
            map = mapShips.testBit(63-i);
            aim = aimShots.testBit(63-i);
            if (map && aim) {
                result.append("☒");
            } else if (aim) {
                result.append("×");
            } else if (map) {
                    result.append("☐");
            } else {
                result.append(".");
            }
            if ((i % 9) == 0) {
                result.insert(i, "\n");
            }
        }
        return result.toString();
    }
}
