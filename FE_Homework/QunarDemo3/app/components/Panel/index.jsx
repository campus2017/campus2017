/**
 * @file component Panel
 */
import './style.scss';
import React, { PropTypes } from 'react';

import SelectBar from '../SelectBar';
import List from '../List';
import PageBar from '../PageBar';

const propTypes = {
	items: PropTypes.array.isRequired,
	stateSelect: PropTypes.string.isRequired,
	onSelectState: PropTypes.func.isRequired
}

function Panel({ items, stateSelect, onSelectState }) {
	return (
		<div className="panel-container">
			<SelectBar stateSelect={ stateSelect } onSelectState={ onSelectState }/>
			<List items={ items }/>
			<PageBar />
		</div>
	);
}

Panel.propTypes = propTypes;

export default Panel;