import styled from "@emotion/styled";
import { keyframes } from "@emotion/react";
import React from "react";

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
const Div = styled.div(({ visible, zIndex }) => {
  const animation = visible ? fadeIn : fadeOut;
  console.debug("backdrop-visible:" + visible);
  return {
    animation: animation + " 300ms",
    visibility: visible ? "visible" : "hidden",
    opacity: visible ? 1 : 0,
    transitionDuration: "300ms",
    transitionDelay: "10ms",
    position: "fixed",
    alignItems: "center",
    inset: "0px",
    backgroundColor: "rgba(0, 0, 0, 0.6)",
    zIndex: zIndex,
    "@media (min-width: 1281px)": {
      zIndex: !visible && 11,
    },
  };
});

export default function Backdrop({ option, onClick: clickEvent }) {
  const { visible, zIndex } = option;
  return <Div onClick={clickEvent} visible={visible} zIndex={zIndex} />;
}
