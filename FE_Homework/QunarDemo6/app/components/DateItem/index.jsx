/**
 * file component DateItem
 */
import './style.scss';
import React, { PropTypes } from 'react';

const propTypes = {
	id: PropTypes.string.isRequired,
	tripInfo: PropTypes.object.isRequired,
	selectedId: PropTypes.string,
	onSelectedDate: PropTypes.func.isRequired
};

function DateItem({ id, tripInfo, selectedId, onSelectedDate }) {
	
	return (
		<li className={ selectedId === id ? 'date-item selected-date' : 'date-item' } onClick={ () => { onSelectedDate(id); } }>
			<span>{ tripInfo.startTime } 去</span>
			<span>{ tripInfo.endTime } 回</span>
			<span className="price">￥ { tripInfo.price } </span>
			<span className={ tripInfo.isLow ? 'low' : 'hide' } >低</span>
		</li>
	);
}

DateItem.propTypes = propTypes;

export default DateItem;