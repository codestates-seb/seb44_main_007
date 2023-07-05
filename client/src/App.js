import React from "react";
import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Home from "./Pages/Home";
import Login from "./Pages/Login";
import SideNavBar from "./Components/SideNavBar";

function App() {
  return (
    <BrowserRouter>
      <SideNavBar />
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/signup" element="회원가입"></Route>
        <Route path="/accountbook" element="가계부"></Route>
        <Route path="/analysis" element="소비패턴분석"></Route>
        <Route path="/wishList" element="위시리스트"></Route>
        <Route path="/premium" element="프리미엄"></Route>
      </Routes>
    </BrowserRouter>
    
  );
}

export default App;

// 스타일,리덕스,라우터
