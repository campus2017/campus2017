/**
 * file component DateList
 */
import './style.scss';
import React, { PropTypes } from 'react';
import DateItem from '../DateItem';

const propTypes = {
	items: PropTypes.array.isRequired,
	selectedId: PropTypes.string.isRequired,
	onSelectedDate: PropTypes.func.isRequired
};

function DateList({ items, selectedId, onSelectedDate }) {
	let itemList = items.map((item) => {
		return <DateItem key={ item.id } id={ item.id } tripInfo={ item.tripInfo }  selectedId={ selectedId }  onSelectedDate={ onSelectedDate }/>
	});
	return (
		<div className="date-list">
			<a className="btn-back" href="#"><span></span></a>
			<ul>
				{ itemList }
			</ul>
			<a className="btn-go" href="#"><span></span></a>
			<span className="calendar"><span></span></span>
		</div>
	);
}

DateList.propTypes = propTypes;

export default DateList;