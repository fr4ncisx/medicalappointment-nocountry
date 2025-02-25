import { AriaRole, ReactNode } from "react";
import { Link } from "react-router";
import { AnchorStyles } from "./AnchorStyles";

type Url = `/${string}` | "#";

interface Props {
    to: Url;
    ariaLabel: string;
    children: ReactNode;
    role?: AriaRole;
    onClick?: () => void;
}

export const Anchor = ({ to, ariaLabel, children, role = "link", onClick = undefined }: Props) => {
    return (
        <Link className="link" to={to} aria-label={ariaLabel} style={AnchorStyles.anchor} role={role} onClick={onClick}>
            {children}
        </Link>
    );
}