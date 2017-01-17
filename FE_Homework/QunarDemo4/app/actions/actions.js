import AppDispatcher from '../dispatcher/AppDispatcher';

const Actions = {
	getDetail(state) {
		AppDispatcher.dispatch({
			actionType: 'GET_DETAIL',
			state
		});
	},
	closeDetail(state) {
		AppDispatcher.dispatch({
			actionType: 'CLOSE_DETAIL',
			state
		});
	}
};

export default Actions;