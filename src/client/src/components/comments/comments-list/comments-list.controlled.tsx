import * as React from 'react';
import { CommentsListProps } from '../../../models/comments.model';
import { CommentComponent } from '../comment/comment.controlled';
import { ReactElement } from 'react';

export const CommentsListComponent = (props: CommentsListProps): ReactElement<HTMLDivElement>[] | any => {
	console.log(props);
	const commentKeys = Object.keys(props.comments);

	return commentKeys.map((key: string) => {
		if (props.comments[key].replies) {
			return (
				<div
					className="comment-with-replies"
				>
					<CommentComponent
						key={key}
						{...props.comments[key]}
					/>
					<div
						className="replies"
					>
						<div>
							<CommentsListComponent
								topic={props.topic}
								onUpdatList={props.onUpdateList}
								comments={props.comments[key].replies}
							/>
						</div>
					</div>
				</div>
			);
		} else {
			return (
				<CommentComponent
					key={key}
					{...props.comments[key]}
				/>
			);
		}
	});
};
