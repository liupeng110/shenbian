//
//  RecommentCardCell.m
//  Miaohi
//
//  Created by 杨绍智 on 16/12/2.
//  Copyright © 2016年 haiqiu. All rights reserved.
//

#import "RecommentCardCell.h"

@implementation RecommentCardCell
- (instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        [self setUp];
    }
    return self;
}

- (void)setUp{
    [self addSubview:self.cellView];
    [self addSubview:self.headImage];
    [self.cellView addSubview:self.nameLable];
    [self.cellView addSubview:self.noteView];
    [self.noteView addSubview:self.perNote];
}
- (UIView*)cellView{
    if (!_cellView) {
        _cellView = [[UIView alloc]initWithFrame:CGRectMake(0, 15, self.width, self.height-15)];
        _cellView.backgroundColor = [UIColor blackColor];
        _cellView.clipsToBounds = YES;
        _cellView.layer.cornerRadius = 5.0;
        _cellView.layer.borderWidth = 0.5;
        _cellView.layer.borderColor = [UIColor colorWithHexColor:@"#dfdfdf"].CGColor;
    }
    return _cellView;
}
- (UIView*)noteView{
    if (!_noteView) {
        _noteView = [[UIView alloc]initWithFrame:CGRectMake(0,50, self.width, self.height-50)];
        _noteView.backgroundColor = [UIColor whiteColor];
        //_noteView.layer.masksToBounds = NO;
        //_FocusContentView.layer.cornerRadius = 5.f;
        //_noteView.layer.shadowOffset = CGSizeMake(.0f,2.5f);
        //_noteView.layer.shadowRadius = 1.5f;
        //_noteView.layer.shadowOpacity = .9f;
        //_noteView.layer.shadowColor = [UIColor colorWithHexColor:@"#dfdfdf"].CGColor;
        //_noteView.layer.shadowPath = [UIBezierPath bezierPathWithRect:_noteView.bounds].CGPath;

    }
    return _noteView;
}

- (UIImageView*)headImage{
    if (!_headImage) {
        _headImage = [[UIImageView alloc]initWithFrame:CGRectMake((self.width-30)/2.0 , 0, 30, 30)];
        _headImage.backgroundColor = [UIColor colorWithHexColor:@"#dfdfdf"];
        _headImage.clipsToBounds = YES;
        _headImage.layer.cornerRadius = 15.0;
        _headImage.layer.borderWidth = 2.0;
        _headImage.layer.borderColor = [UIColor whiteColor].CGColor;
    }
    return _headImage;
}

- (UILabel*)nameLable{
    if (!_nameLable) {
        _nameLable = [[UILabel alloc]initWithFrame:CGRectMake(0, 30, self.width, 15)];
        _nameLable.textColor = [UIColor colorWithHexColor:@"#ffffff"];
        _nameLable.font = [UIFont systemFontOfSize:13.0];
        _nameLable.textAlignment = NSTextAlignmentCenter;
        _nameLable.text = @"用户姓名";
    }
    return _nameLable;
}

- (UILabel*)perNote{
    if (!_perNote) {
        _perNote = [[UILabel alloc]initWithFrame:CGRectMake(5, 5, self.width-10,self.noteView.height-10)];
        _perNote.textColor = [UIColor colorWithHexColor:@"#999999"];
        _perNote.numberOfLines =0;
        _perNote.adjustsFontSizeToFitWidth = YES;
        _perNote.font = [UIFont systemFontOfSize:10.0];
        _perNote.text = @"用户描述内容用户描述内容用户";
    }
    return _perNote;
}
@end
