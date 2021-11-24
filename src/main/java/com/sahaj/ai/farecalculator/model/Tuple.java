/**
 * 
 */
package com.sahaj.ai.farecalculator.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class Tuple<X, Y>
{
	public final int x ;
	public final int y ;

	public Tuple(int fromZone, int toZone) {
		this.x = fromZone;
		this.y = toZone;
		// TODO Auto-generated constructor stub
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}
