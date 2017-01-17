import AppDispatcher from '../dispatcher/AppDispatcher';

const Actions = {
	selected(id) {
		AppDispatcher.dispatch({
			actionType: 'SELECT_DATE',
			id
		});
	}
}

export default Actions;