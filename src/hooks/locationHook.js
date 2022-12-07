import { useLocation as useWouterLocation } from "wouter";

function getSearchParam(query) {
  return Object.fromEntries(new URLSearchParams(query)).search;
}

function getSortParam(query) {
  return Object.fromEntries(new URLSearchParams(query)).sort;
}

function getPageParam(query) {
  return Object.fromEntries(new URLSearchParams(query)).page;
}

export const useLocation = () => {
  const [location, setLocation] = useWouterLocation();
  const locationSearch = window.location.search;
  const query = {
    base: locationSearch,
    search: getSearchParam(locationSearch)||"",
    sort: getSortParam(locationSearch)||"",
    page: getPageParam(locationSearch)||"",
  };
  return [location, setLocation, query];
};
