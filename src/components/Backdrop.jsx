import styled from "@emotion/styled";
import { keyframes } from "@emotion/react";
import React from "react";

const Div = styled.div(({ visible, zIndex }) => {
  console.debug("backdrop-visible:" + visible);
  return {
    transition: "300ms",
    visibility: visible ? "visible" : "hidden",
    opacity: visible ? 1 : 0,
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
