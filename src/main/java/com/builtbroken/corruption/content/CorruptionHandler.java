package com.builtbroken.corruption.content;

import com.builtbroken.jlib.data.Colors;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by robert on 1/26/2015.
 */
public class CorruptionHandler
{
    public static final HashMap<Integer, Color> dimToColor = new HashMap();

    static
    {
        setCorruptionColor(0, Colors.CORRUPTION_PURPLE.color());
        setCorruptionColor(1, Colors.DARK_BLUE.color());
        setCorruptionColor(-1, Colors.BLACK.color());
    }

    public static void setCorruptionColor(int dim, Color color)
    {
        dimToColor.put(dim, color);
    }

    public static  Color getCorruptionColor(int dim)
    {
        return dimToColor.containsKey(dim) ? dimToColor.get(dim) : Colors.CORRUPTION_PURPLE.color();
    }
}
