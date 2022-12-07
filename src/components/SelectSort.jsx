import React, { useEffect, useState } from "react";
import Select from "react-select";
import { useLocation } from "@/hooks/locationHook";
const options = [
  { value: "latest", label: "Sort by latest" },
  { value: "oldest", label: "Sort by oldest" },
  { value: "name_asc", label: "Sort by name A-Z" },
  { value: "name_desc", label: "Sort by name Z-A" },
];

export function SelectSort() {
  const [selectedOption, setSelectedOption] = useState(null);
  const [, setLocation, query] = useLocation();
  const handleChange = (option) => {
    setSelectedOption(option);
    setLocation(`?search=${query.search}&sort=${option.value}&page=${query.page}`);
  };
  return (
    <Select
      isSearchable={false}
      placeholder="Sort"
      defaultValue={selectedOption}
      onChange={handleChange}
      options={options}
    />
  );
}
