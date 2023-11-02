import { useState, useEffect } from "react";
import axios from "axios";

export default function useFetch(url) {
  const [data, setData] = useState([]);

  const fetchData = async () => {
    const res = await axios.get(url);
    setData(res.data);
  };

  useEffect(() => {
    fetchData();
  }, [url]);

  return data;
}
