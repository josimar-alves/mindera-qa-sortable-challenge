import React, { Component } from 'react';
import { render } from 'react-dom';
import { SortableContainer, SortableElement, arrayMove } from 'react-sortable-hoc';

for (var a = [], i = 0; i < 6; ++i) a[i] = i;

function shuffle(array) {
  var tmp, current, top = array.length;
  if (top) while (--top) {
    current = Math.floor(Math.random() * (top + 1));
    tmp = array[current];
    array[current] = array[top];
    array[top] = tmp;
  }
  return array;
}
a = shuffle(a);

const SortableItem = SortableElement(({ value }) =>
  <li>{value}</li>
);

const SortableList = SortableContainer(({ items }) => {
  return (
    <ul>
      {items.map((value, index) => (
        <SortableItem key={`item-${index}`} index={index} value={value} />
      ))}
    </ul>
  );
});

class SortableComponent extends Component {

  state = {
    items: [`Item ${a[0]}`, `Item ${a[1]}`, `Item ${a[2]}`, `Item ${a[3]}`, `Item ${a[4]}`, `Item ${a[5]}`],
  };
  onSortEnd = ({ oldIndex, newIndex }) => {
    this.setState({
      items: arrayMove(this.state.items, oldIndex, newIndex),
    });
  };
  render() {
    return <SortableList items={this.state.items} onSortEnd={this.onSortEnd} helperClass="sortable" />;
  }
}

render(<SortableComponent />, document.getElementById('app'));
