package com.redmark.Sanchalak.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Query
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Query {
	private String prefix = "";
	private String message;
	private String postfix = "";
	private String model;

	public String getQuery() {
		return prefix + " .\n" + message + " .\n" + postfix;
	}

}
