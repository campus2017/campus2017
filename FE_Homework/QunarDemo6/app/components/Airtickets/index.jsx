/**
 * file componet Airtickets
 */
import './style.scss';
import React from 'react';

import DateList from '../DateList';
import FlightList from '../FlightList';

import Actions from '../../actions/Actions';
import Stores from '../../stores/Stores';

class Airtickets extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			items: Stores.getItems(),
			selectedId: Stores.getSelectedId()
		};
		this.selectedDate = this.selectedDate.bind(this);
		this.onChange = this.onChange.bind(this);
	}
	
	componentDidMount() {
		Stores.addChangeListener(this.onChange);
	}
	
	componentWillUnmount() {
		Stores.removeChangeListener(this.onChange);
	}

	onChange() {
		this.setState({
			selectedId: Stores.getSelectedId()
		});
	}

	selectedDate(id) {
		Actions.selected(id);
	}

	render() {
		return (
			<div className="air-tickets">
				<DateList items={ this.state.items } selectedId={ this.state.selectedId } onSelectedDate={ this.selectedDate } />
				<FlightList flightInfos={ this.state.items[this.state.selectedId].flightInfos } />
			</div>
		);
	}
}

export default Airtickets;