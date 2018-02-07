import * as React from 'react';
import { ReactElement, SyntheticEvent } from 'react';
import {
	COMMENTS_COMPONENT_STATE_INIT, CommentsComponentProps,
	CommentsComponentState
} from '../../models/comments.model';
import { CommentsListComponent } from './comments-list/comments-list.controlled';
import { getComments, getCommentsListMeta } from '../../services/comments.service';

export class CommentsComponent extends React.Component<CommentsComponentProps, CommentsComponentState> {
	private formText = '';

	constructor(public props: CommentsComponentProps) {
		super(props);
		this.state = COMMENTS_COMPONENT_STATE_INIT;
		this.updateListHandler = this.updateListHandler.bind(this);
	}

	public async componentDidMount(): Promise<void> {
		const listMeta = await getCommentsListMeta(this.props.viewId);
		const comments = await getComments(listMeta.listId);
		console.log('comments', comments);
		this.setState({
			commentListMeta: listMeta,
			comments: comments,
			dataReady: true
		});
	}

	public render(): ReactElement<HTMLDivElement> {
		return(
			<div
				className="comments-component"
			>
				<div
					className="comments-header"
				>
					<h1>Comments</h1>
				</div>
				{this.renderCommentForm()}
				<div
					className="comments-list"
				>
					{this.state.dataReady
						? <CommentsListComponent
							onUpdateList={this.formHandler}
							comments={this.state.comments}
							{...this.state.commentListMeta}
						/> : <div>
							Loading Comments .......
						</div>
					}
				</div>
			</div>
		);
	}

	private formHandler(): void {
		console.log('current text', this.formText);
	}

	private updateListHandler(evt: Event): void {
		console.log('updated', evt);
	}

	private renderCommentForm(): ReactElement<HTMLFormElement> {
		return(
			<form
				id="CommentForm"
			>
				<div
					className="comment-input"
					suppressContentEditableWarning={true}
					contentEditable={true}
					onClick={() => this.setState({formPristine: false})}
					onInput={(event: React.FormEvent<HTMLElement>) => {
						this.formText = (event.target as any).innerHTML;
					}}
				>
					{this.state.formPristine ? 'Leave a comment!' : ''}
				</div>
				<div
					className="controls"
				>
					<button
						className="btn btn-success btn-lg"
						onClick={(evt: SyntheticEvent<MouseEvent> | any) => {
							evt.preventDefault();
							this.formHandler();
						}}
					>
						Submit
					</button>
				</div>
			</form>
		);
	}
}
