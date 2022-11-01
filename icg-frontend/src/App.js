import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { HomePage } from './pages/home/HomePage';
import { LoginPage } from './pages/login/LoginPage';
import { RegisterPage } from './pages/register/RegisterPage';
import { AdminPage } from './pages/admin/AdminPage';
import { NotFoundPage } from './pages/not-found/NotFoundPage';
import { UnauthorizedPage } from './pages/unauthorized/UnauthorizedPage';
import { AuthGuard } from './guards/AuthGuards';
import { Role } from './models/Role';
import {NavBar} from "./components/Navbar";




function App() {
    return (
        <BrowserRouter>
            <NavBar/>
            <div className="container">
                <Routes>
                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/home" element={<HomePage/>}/>
                    <Route path="/admin" element={<AdminPage/>}/>
                    <Route path="/login" element={<LoginPage/>}/>
                    <Route path="/register" element={<RegisterPage/>}/>

                    <Route path="/home" element={
                        <AuthGuard roles={[Role.ADMIN]}>
                            <HomePage/>
                        </AuthGuard>
                    }
                    />

                    <Route path="/admin" element={
                        <AuthGuard roles={[Role.ADMIN]}>
                            <AdminPage/>
                        </AuthGuard>
                    }/>

                    <Route path="/404" element={<NotFoundPage/>}/>
                    <Route path="/401" element={<UnauthorizedPage/>}/>
                    <Route path="*" element={<NotFoundPage/>}/>
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
