/**
 * @file component DateList
 */
import './style.scss';
import React, { PropTypes } from 'react';

const propTypes = {

};

function DateList() {
	return (
		<div className="date-container">
			<span>选择日期：</span>
			<input type="date" name="date-start" />
			<span className="to"> 至 </span>
			<input type="date" name="date-end" />
		</div>
	);
}

DateList.propTypes = propTypes;

export default DateList;