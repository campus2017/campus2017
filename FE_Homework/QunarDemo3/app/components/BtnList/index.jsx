/**
 * @file component BtnList
 */
import './style.scss';
import React, { PropTypes } from 'react';

const propTypes = {
	stateSelect: PropTypes.string.isRequired,
	onSelectState: PropTypes.func.isRequired
};

class BtnList extends React.Component {
	render() {
		let stateSelect = this.props.stateSelect,
			selectState = this.props.onSelectState;
		let onSelect = (that, state) => {
			if (that.className === 'state-bg') {
				return;
			}
			selectState(state);
		};
		return (
			<div className="state-btn-container">
				<a href="#" ref="all" className={ stateSelect === "all" ? "state-bg" : ""} onClick={ () => onSelect(this.refs.all, 'all') } >全部</a>
				<a href="#" ref="success" className={ stateSelect === "success" ? "state-bg" : ""} onClick={ () => onSelect(this.refs.success, 'success') }>成功</a>
				<a href="#" ref="fail" className={ stateSelect === "fail" ? "state-bg" : ""} onClick={ () => onSelect(this.refs.fail, 'fail') }>失败</a>
			</div>
		);
	}
}

BtnList.propTypes = propTypes;

export default BtnList;
