/* tslint:disable */
import * as React from 'react';
import {
	CommentProps,
	CommentsList, CommentsListProps, CommentsListState,
	generateDemoComment
} from '../../../models/comments.model';
import * as renderer from 'react-test-renderer';
import { CommentsListComponent } from './comments-list.controlled';
declare var window: any;

/**
 * Faking the Func, Fetch :)
 * @param val
 */
export function setFetchReturnVal(val: any){
	window.fetch = async function(url?: string, init?: any){
		return{
			json(){
				return val;
			}
		}
	};
}

/**
 * Testing
 * Set up dummy data.
 */
const FIXTURE_COMMENTS_LIST: CommentsList = {
	testComment0: generateDemoComment(),
	testComment1: generateDemoComment(),
	testComment2: generateDemoComment(),
};
const FIXTURE_COMMENTS_LIST_PROPS: CommentsListProps = {
	onUpdateList: () => {
		return;
	},
	comments: FIXTURE_COMMENTS_LIST,
	listId: 'testList0',
	topic: 'testView'
};

setFetchReturnVal(FIXTURE_COMMENTS_LIST);

/**
 * Testing
 * Get component(s) to test.
 */
const component: any = renderer.create(
	<CommentsListComponent
		{...FIXTURE_COMMENTS_LIST_PROPS}
	/>
);

/**
 * Testing
 * Write tests :)
 */
it(
	`Should have viewId for comment data services`,
	() => {
		expect(
			component.toTree().props.topic
		)
			.toBe(FIXTURE_COMMENTS_LIST_PROPS.topic);
	}
);

it(
	`Should render comments in order by latest to oldest`,
	() => {
		expect(testDates()).toBeTruthy();

		function testDates(): boolean {
			const currentCommentOrder = Object
				.keys(component.toTree().instance.state)
				.map(key => component.toTree().instance.state[key]);
			let lastDate = currentCommentOrder[0].create_date;

			for (let comment of currentCommentOrder) {
				if (lastDate < comment.create_date) {
					return false;
				}
				lastDate = comment.create_date;
			}
			return true;
		}
	}
);

it(
	`Should have a true editable prop if userId matches comment's user_id.`,
	() => {
		function compareCommentsEditable(): boolean {
			const comments: CommentsListState = component
				.toTree().instance.state;
			const commentsArray: CommentProps[] = Object
				.keys(comments)
				.map(key => {
					return comments[key];
			});
			const userId = commentsArray[1].userId;
			for (let comment of commentsArray) {
				console.log('COMMENT FOR LOOP', comment, userId);
				if (comment.userId === userId && !comment.editable) {
					return false;
				} else if (comment.editable) {
					return false;
				}
			}
			return true;
		}
		expect(compareCommentsEditable())
			.toBe(true);
	}
);
