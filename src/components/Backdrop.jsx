import styled from "@emotion/styled";
import { keyframes } from "@emotion/react";

const fadeIn = keyframes`
  0% {
    opacity: 0;
  }

  100%{
    opacity: 1;
  }

`;
const fadeOut = keyframes`
  0% {
    opacity: 1;
  }

  100%{
    opacity: 0;
  }

`;
const Div = styled.div(({ visible }) => {
  const animation = visible ? fadeIn : fadeOut;
  console.log("backdrop-visible:" + visible);
  return {
    animation: `${animation} 400ms`,
    visibility: visible ? "visible" : "hidden",
    transitionDuration: "200ms",
    position: "fixed",
    alignItems: "center",
    inset: "0px",
    backgroundColor: "rgba(0, 0, 0, 0.6)",
    zIndex: 10,
  };
});

export default function Backdrop({ visible, onClick: clickEvent }) {
  return <Div onClick={clickEvent} visible={visible} />;
}
