import {Component, OnInit} from '@angular/core';
import {UselessFactsService} from "../../../../core/service/useless-facts.service";

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent implements OnInit {

  public uselessFact: string;

  constructor(private uselessFactsService: UselessFactsService) {
  }

  ngOnInit(): void {
    this.uselessFactsService.getUselessFactToday().subscribe(x => this.uselessFact = x.text)
  }



}
