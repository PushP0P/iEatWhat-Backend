import { CommentsList, CommentsListMeta, generateDemoComment } from '../models/comments.model';
import { openIDBUtilities } from 'indexed-db-utilities/dist/utilities/index-db.utility';
let db = openIDBUtilities({
	version: 1,
	dbName: 'dev-user-data',
	storeNames: ['userData', 'tokens', 'state'],
	keyPath: 'uDKey'
});

export async function getUserComments(): Promise<CommentsList> {
	const stores = await db;
	const userData = await stores.get('userData', 'account');
	return {
		'Message123': generateDemoComment(userData.id),
		'Message143': generateDemoComment(userData.id),
		'Message423': generateDemoComment(userData.id),
	};
}

export async function getComments(listId: string): Promise<CommentsList> {
	const stores = await db;
	const userData = await stores.get('userData', 'account');
	// const responsePack = await fetch(`//localhost:5000/${listId}`);
	// const response: CommentsList = await responsePack.json();

	// FIXTURES
	const responsePack = await getUserComments();
	const response: CommentsList = responsePack;
	// END
	return enableEditForUserComments(response, userData.id);
}

export async function getCommentsListMeta(containerId: string): Promise<CommentsListMeta> {
	return {
		listId: containerId,
		topic: 'Demo',
	};
}

/**
 * Helpers
 */

function enableEditForUserComments(comments: CommentsList, userId: string): CommentsList {
	return Object.keys(comments).reduce(
		(agg: CommentsList, key: string) => {
			if (comments[key].userId === userId) {
				return {...{}, ...agg, [key]: {...comments[key], editable: true}};
			}
			return {...{}, ...agg, [key]: {...comments[key]}};
		},
		{}
	);
}
