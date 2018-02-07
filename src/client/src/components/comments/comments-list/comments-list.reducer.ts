import { Action } from '../../../models/action.model';
import { CommentsListState } from '../../../models/comments.model';

export function commentsListReducer(action: Action, currentState: CommentsListState): CommentsListState {

	switch ( action.type ) {
		case'ADD_COMMENT':
			return {...{}, ...currentState, ...action.payload};
		case'REMOVE_COMMENT':
			let tempState: CommentsListState = {...{}, ...currentState};
			delete tempState[action.payload];
			return { ...{}, ...tempState};
		default:
			return currentState;
	}
}
