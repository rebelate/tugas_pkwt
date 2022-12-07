import styled from "@emotion/styled";
import { keyframes } from "@emotion/react";
import { logout } from "@/slices/authSlice";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
const slideRight = keyframes`
0% {
  transform:translateX(-300px) !important;
}

100%{
  opacity: 1;
}
`;

const Section = styled.section(({ visible }) => {
  return {
    transition: ".3s cubic-bezier(.86,0,.07,1)",
    overflowY: "scroll",
    "@media (max-width: 1280px)": {
      animation: visible ? slideRight + ".3s " : "none",
      transform: !visible ? "translateX(-300px)" : "none",
      visibility: !visible && "hidden",
    },
  };
});
export function Sidebar({visibility, onClick: clickEvent}) {
  const dispatch = useDispatch();
  const userProfile = useSelector((state) => state.auth.user);
  return (
    <Section id="sidebar" visible={visibility}>
      <div className="container">
        <div className="profile">
          {/* <img src="assets/profile.png" /> */}
          <img src="https://pbs.twimg.com/media/D7ShRPYXoAA-XXB.jpg" />
          <h3>{userProfile.name}</h3>
          <a
            style={{ cursor: "pointer" }}
            onClick={() => {
              dispatch(logout());
              setLocation("/login");
            }}
          >
            &#xf08b; Logout
          </a>
        </div>

        <div className="nav">
          <a href="#" className="text-dark text-bold btn btn-link">
            Explore
          </a>
          <a href="#" className="text-dark text-bold btn btn-link">
            History
          </a>
          {userProfile.admin && (
            <a
              onClick={clickEvent}
              id="add-book"
              className="text-dark text-bold btn btn-link"
            >
              Add book *
            </a>
          )}
        </div>
      </div>
    </Section>
  );
}
