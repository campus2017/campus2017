/**
 * @file component SelectBar
 */
import './style.scss';
import React, { PropTypes } from 'react';
import BtnList from '../BtnList';
import DateList from '../DateList';
import SearchList from '../SearchList';

const propTypes = {
	stateSelect: PropTypes.string.isRequired,
	onSelectState: PropTypes.func.isRequired
};

function SelectBar({ stateSelect, onSelectState }) {
	return (
		<div className="select-container">
			<BtnList stateSelect={ stateSelect } onSelectState={ onSelectState }/>
			<DateList />
			<SearchList />
		</div>
	);
}

SelectBar.propTypes = propTypes;

export default SelectBar;