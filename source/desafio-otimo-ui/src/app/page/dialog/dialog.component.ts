import { Component, OnInit, Input } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent implements OnInit {

  @Input()
  title: string;

  @Input()
  messages: Array<string>;

  @Input()
  type: string;

  @Input()
  closeable: boolean;

  @Input()
  cancelable: boolean;

  @Input()
  confirmable: boolean;

  constructor(public ngbActiveModal: NgbActiveModal) { }

  ngOnInit(): void {
  }

  public configure(title: string, msg: string|Array<string>, type: string = 'primary', closeable: boolean = true,
                   cancelable: boolean = false, confirmable: boolean = false): void {
    this.title = title;
    this.type = type;
    this.closeable = closeable;
    this.cancelable = cancelable;
    this.confirmable = confirmable;
    if (msg instanceof Array) {
      this.messages = msg;
    } else {
      this.messages = [msg];
    }
  }

}
