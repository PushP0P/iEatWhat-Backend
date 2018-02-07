export interface CommentProps {
	commentId: string;
	name: string;
	userId: string;
	create_date: number;
	text: string;
	editable: boolean;
	removed: boolean;
	reported: boolean;
	reports: number;
	likes: number;
	replies?: CommentsList;
}

export interface CommentsComponentState {
	formPristine: boolean;
	dataReady: boolean;
	commentListMeta: CommentsListMeta;
	comments: CommentsList;
}

export interface CommentsComponentProps {
	viewId: string;
}

export const COMMENTS_COMPONENT_STATE_INIT: CommentsComponentState = {
	formPristine: true,
	dataReady: false,
	commentListMeta: <CommentsListMeta> {},
	comments: <CommentsList> {}
};

export interface CommentsListMeta {
	listId: string;
	topic: string;
}

export interface CommentsListProps extends CommentsListMeta {
	onUpdateList: () => void;
	comments: CommentsList;
}

export interface CommentsListState {
	dataReady: boolean;
	comments: CommentsList;
}

export interface CommentsList {
	[commentId: string]: CommentProps;
}

export function generateDemoComment(userId?: string): CommentProps {
	return {
		commentId: Math.random().toString(),
		name: 'Demo Dave',
		userId: userId || Math.random().toString(),
		create_date: Date.now(),
		text: `Lorem Ipsum Foo Bat Metal ${Math.random()} times`,
		editable: false,
		removed: false,
		reported: false,
		reports: 0,
		likes: 0
	};
}
