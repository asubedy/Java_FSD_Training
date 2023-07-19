// Pagination.js

import React from 'react';
import ReactPaginate from 'react-paginate';
import './Paginate.css'; // Import the CSS file for styles

const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  const isFirstPage = currentPage === 1;
  const isLastPage = currentPage === totalPages;

  return (
    <ReactPaginate
      previousLabel={'Previous'}
      nextLabel={'Next'}
      pageCount={totalPages}
      marginPagesDisplayed={1}
      pageRangeDisplayed={3}
      forcePage={currentPage - 1}
      onPageChange={(selectedItem) => onPageChange(selectedItem.selected + 1)}
      containerClassName={'pagination'}
      activeClassName={'active'}
      previousLinkClassName={isFirstPage ? 'pagination-link disabled' : 'pagination-link'}
      nextLinkClassName={isLastPage ? 'pagination-link disabled' : 'pagination-link'}
      breakClassName={'pagination-break'}
      pageClassName={'pagination-page'}
    />
  );
};

export default Pagination;
