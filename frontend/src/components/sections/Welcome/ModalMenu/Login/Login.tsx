import { LoginForm } from "./LoginForm";

export default function Login() {
    
    return (
        <form onSubmit={(e) => e.preventDefault()}>
            <LoginForm />
        </form >
    );
}