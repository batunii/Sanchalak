package com.redmark.Sanchalak.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Parameters
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Parameters {

	private int max_new_tokens = 5000;
	private double temperature = 0.01;
	private int top_k = 50;
	private double top_p = 0.95;
	private boolean return_full_text = false;

}
