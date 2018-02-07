import * as React from 'react';
import { CommentProps } from '../../../models/comments.model';
import * as moment from 'moment';
import { SVGS } from '../../../assets/react-svgs.asset';

interface MetaBoxProps {
	name: string;
	date: number;
	userId: string;
}

interface TextBoxProps {
	text: string;
}

interface OptionsBoxProps {
	editable: boolean;
}

const CommentMetaBox = (props: MetaBoxProps) => {
	return (
			<div
				className="comment_meta"
			>
				<div
					className="comment_name"
				>
					<h3>{props.name}</h3>
				</div>
				<div
					className="comment_date"
				>
					{moment(props.date)
						.format('MMM Do YY')}
				</div>
				<div
					className="comment_share"
				>
					{SVGS.share}
				</div>
			</div>
	);
};
const CommentText = (props: TextBoxProps) => {
	return (
		<div
			className="comment_text"
		>
			<div
				className="comment_avatar float-left"
			>
				{SVGS.hipster}
			</div>
			{props.text}
		</div>
	);
};
const OptionsBox = (props: OptionsBoxProps) => {
	return (
		<div
			className="options_box"
		>
			<div
				className="options_edit"
				hidden={props.editable}
			>
				{SVGS.pencil}
			</div>
			<div
				className="options_remove"
				hidden={props.editable}
			>
				Remove
			</div>
			<div
				className="options_reply"
				hidden={!props.editable}
			>
				Reply
			</div>
		</div>
	);
};

export const CommentComponent = (props: CommentProps) => {
	return (
		<div
			className="comment-component"
		>
			<CommentMetaBox
				name={props.name}
				date={props.create_date}
				userId={props.userId}
			/>
			<CommentText
				text={props.text}
			/>
			<OptionsBox
				editable={props.editable}
			/>
		</div>
	);
};
