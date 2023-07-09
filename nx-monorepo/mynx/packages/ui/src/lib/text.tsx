export interface Props {
  placeholder: string,
}

export default function Text(props: Props) {
  return (
    <input type="text" placeholder={props.placeholder} />
  );
}
